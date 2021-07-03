import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BusquedaComponent} from './pages/busqueda/busqueda.component';

const routes: Routes = [
  {path: '', component: BusquedaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
