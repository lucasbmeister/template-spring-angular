import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "@shared/components/login/login.component";
import { AnonymousGuard } from "@shared/auth/anonymous.guard";
import { AuthGuard } from "@shared/auth/auth.guard";
import { NotFoundComponent } from "@shared/components/not-found/not-found.component";

const routes: Routes = [
    { path: '', redirectTo: '/app/home', pathMatch: 'full' },
    {
        path: 'login',
        component: LoginComponent,
		canActivate : [AnonymousGuard]
    },
    {
        path: 'app',
        loadChildren: './app/app.module#AppModule',
		canActivate : [AuthGuard],
		data : {permissions : ["ADMIN"]}
    },
	{
		path : "**",
		component : NotFoundComponent
	}
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class RootRoutingModule { }