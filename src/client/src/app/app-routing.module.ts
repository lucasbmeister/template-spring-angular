import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { AuthGuard } from '@shared/auth/auth.guard';

const routes: Routes = [
 	{	
		path : "",
		component : AppComponent,
		children : [
			{ path: 'home', component : HomeComponent },
			{ 
				path: 'users', 
				loadChildren : "./users/users.module#UsersModule", 
				canActivate : [AuthGuard], 
				data : {permissions : ["ADMIN"]} 
			}
		]
	}
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
