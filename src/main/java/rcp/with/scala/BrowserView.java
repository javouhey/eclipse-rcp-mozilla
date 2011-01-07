package rcp.with.scala;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.mina.common.ByteBuffer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class BrowserView extends ViewPart {

    public final static String ID = "view.browser";

    private Composite m_parent;
    private Browser m_browser;

    private String readFully( InputStream istream )
        throws IOException
    {
        ByteBuffer buffer = ByteBuffer.allocate( 2048 );
        buffer.setAutoExpand( true );

        int i = -1;
        BufferedInputStream bufStream = new BufferedInputStream( istream );

        while( (i = bufStream.read()) != -1 )
        {
            buffer.put( (byte)i );
        }

        Charset charset = Charset.forName( "UTF-8" );
        return buffer.flip().getString( charset.newDecoder() ); // may throw java.nio.charset.CharacterEncodingException
    }

    @Override
    public void createPartControl(Composite parent) {
        m_parent = parent;

        try {
//            if( true )
//                throw new RuntimeException( "boo" );

            m_browser = new Browser( parent, SWT.MOZILLA | SWT.BORDER );
        } catch (RuntimeException e) {
            parent.setLayout(new FillLayout());
            Label label = new Label(parent, SWT.CENTER | SWT.WRAP);
            label.setText( "Mozilla browser could not be created" );
            parent.layout(true);
            return;
        }

        Bundle bundle = Activator.getDefault().getBundle();
        URL homepageUrl = bundle.getEntry( "/res/homepage.html" ); // may throw ISE

        String defaultHomepage = "<html><head><title>bonjour</title></head><body><span style='text-align:center; display: block;'><object width='350' height='145'><param name='movie' value='http://www.youtube.com/v/ZCARH83vZpw'></param><param name='wmode' value='transparent'></param><embed src='http://www.youtube.com/v/ZCARH83vZpw&#038;rel=0' type='application/x-shockwave-flash' wmode='transparent' width='350' height='145'></embed></object></span></body></html>";

        String forDisplay = "";

        try {

// The returned URL is not a file URL; thus I cannot use the FileReader on it
//            BufferedReader reader = new BufferedReader( new FileReader( new File( homepageUrl.toURI() )) );
//            forDisplay = reader.readLine();

            forDisplay = readFully( homepageUrl.openStream() );
            System.out.println( "forDisplay => " + forDisplay );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            forDisplay = defaultHomepage;

        } catch (IOException e) {
            e.printStackTrace();
            forDisplay = defaultHomepage;
        }
        finally
        {
            m_browser.setText( forDisplay );
        }

        //m_browser.execute( "javascript:alert('helloworld')" );
        //m_browser.setUrl( "http://www.youtube.com" );
    }

    @Override
    public void setFocus() {
        if( m_browser != null )
            m_browser.setFocus();
    }

    @Override
    public void dispose()
    {
        if( m_browser != null )
        {
            m_browser.dispose();
            System.out.println( "BrowserView.dispose" );
            m_browser = null;
        }
        super.dispose();
    }
}
