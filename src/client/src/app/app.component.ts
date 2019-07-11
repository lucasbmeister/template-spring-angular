import { Component, OnInit } from '@angular/core';

import { ThfMenuItem, ThfToolbarAction, ThfToolbarProfile } from '@totvs/thf-ui';
import { AuthenticationService } from '@shared/services/security/authentication.service';
import { ThfMenuItemWithPermission } from '@shared/interfaces/thf-menu-item-with-permission.interface';
import { UserDto } from '@shared/dtos/security/users/user-dto';

@Component({
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

	userName : string = "";

	readonly profileActions: Array<ThfToolbarAction> = [
		{ action: this.logout.bind(this), label: "Sair", icon: "thf-icon-exit", type: "danger" }
	];

	private profile: ThfToolbarProfile = { title: this.userName }

	menus: Array<ThfMenuItemWithPermission> = [
		{ label: 'Home', link: 'home', icon: "thf-icon-home" },
		{
			label: 'Configurações', icon: "thf-icon-settings", permission : "ADMIN",
			subItems: [
				{ label: "Usuários", link: "users", permission : "ADMIN"}
			]
		},
	];

	filteredMenu : Array<ThfMenuItemWithPermission> = new Array<ThfMenuItemWithPermission>();

	constructor(private authenticationService: AuthenticationService) { }

	ngOnInit(): void {
		this.getMenus();
	}

	logout() {
		this.authenticationService.logout();
	}

	getMenus() {
		this.authenticationService.getUser().subscribe(user => {
			this.filteredMenu = this.filterMenu(user, this.menus)
			this.userName = user.name;
		})
	}

	filterMenu(user : UserDto, menus : Array<ThfMenuItemWithPermission>) : Array<ThfMenuItemWithPermission> {
		return menus.filter((menu : any) => {
			var  hasPermission = user.hasRole(menu.permission)
			if(!hasPermission){
				return false;
			} else if(hasPermission && menu.subItems){
				menu.subItems = this.filterMenu(user, menu.subItems);
				return true;
			} else {
				return true;
			}
		})
	}

}
