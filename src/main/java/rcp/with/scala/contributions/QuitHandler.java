package rcp.with.scala.contributions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class QuitHandler extends AbstractHandler {

    @Override
    public Object execute( ExecutionEvent arg0 )
        throws ExecutionException
    {
        System.out.println( "hello" );
        return null;
    }

}
