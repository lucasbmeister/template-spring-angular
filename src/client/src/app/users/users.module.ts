import { NgModule } from "@angular/core";
import { UsersComponent } from "./users.component";
import { CreateUserComponent } from "./create-user/create-user.component";
import { EditUserComponent } from "./edit-user/edit-user.component";
import { SharedModule } from "@shared/shared.module";
import { UsersRoutingModule } from "./users-routing.module";
import { DetailUserComponent } from './detail-user/detail-user.component';


@NgModule({
	imports : [
		UsersRoutingModule,
		SharedModule
	],
	declarations : [
		UsersComponent,
		CreateUserComponent,
    	EditUserComponent,
    	DetailUserComponent
	],
})

export class UsersModule {

}