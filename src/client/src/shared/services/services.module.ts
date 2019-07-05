import { NgModule } from '@angular/core';
import { AuthenticationService } from './security/authentication.service';
import { SecurityService } from './security/security.service';
import { UserService } from './security/user.service';
import { RoleService } from './security/role.service';

@NgModule({
	providers :[
		AuthenticationService,
		SecurityService,
		UserService,
		RoleService
	]
})
export class ServicesModule { }
