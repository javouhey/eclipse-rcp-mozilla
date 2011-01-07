# What

To demonstrate the embedding of mozilla's browser inside an eclipse RCP (rich client platform) client.

The dependencies are implied in the manifest:

    Manifest-Version: 1.0
    Bundle-ManifestVersion: 2
    Bundle-Name: RCP with scala
    Bundle-SymbolicName: rcp.with.scala; singleton:=true
    Bundle-Version: 1.0.0
    Bundle-Activator: rcp.with.scala.Activator
    Bundle-Vendor: Gavin Bong
    Require-Bundle: org.eclipse.ui,
      org.eclipse.core.runtime,
      org.eclipse.core.expressions,
      org.apache.mina.core,
      slf4j.api,
      slf4j.log4j12
    Eclipse-LazyStart: true



# Licence

Copyright (C) 2008 Gavin Bong. Distributed under the [Apache 2.0 license](http://www.apache.org/licenses/LICENSE-2.0.html "license details").
