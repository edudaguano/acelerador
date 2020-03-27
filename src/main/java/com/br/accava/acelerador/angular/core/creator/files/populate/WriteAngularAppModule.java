package com.br.accava.acelerador.angular.core.creator.files.populate;

import com.br.accava.acelerador.angular.core.creator.files.interfaces.WriteFile;

public class WriteAngularAppModule implements WriteFile {

    @Override
    public void writeImports(){
        //import { $componentImport$} from './pages/$foldername$/$componentname$'
    }

    private void writeDeclarations(){
        /*
         declarations: [
            AppComponent
            $declarationModule$
          ],
         */
    }


}
