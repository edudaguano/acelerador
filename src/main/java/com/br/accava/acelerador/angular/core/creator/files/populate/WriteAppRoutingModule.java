package com.br.accava.acelerador.angular.core.creator.files.populate;

import com.br.accava.acelerador.angular.core.creator.files.interfaces.WriteFile;

public class WriteAppRoutingModule implements WriteFile {

    @Override
    public void writeImports(){
        //import { $componentImport$} from './pages/$foldername$/$componentname$'
    }
    private void writeRoutes(){
        /*
            const routes: Routes = [
              { path: 'login', component: LoginComponent },
              { path: 'add-user', component: AddUserComponent },
              { path: 'list-user', component: ListUserComponent },
              { path: 'edit-user', component: EditUserComponent },
              {path : '', component : LoginComponent}
            ];
         */
    }
}
