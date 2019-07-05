import { Component } from '@angular/core';

import { ThfMenuItem } from '@totvs/thf-ui';

@Component({
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  readonly menus: Array<ThfMenuItem> = [
    { label: 'Home', link: 'home', icon : "thf-icon-home" },
    { label: 'Configurações', icon : "thf-icon-settings",
		subItems : [ 
			{label : "Usuários", link : "users"}
		]
	},
  ];

}
