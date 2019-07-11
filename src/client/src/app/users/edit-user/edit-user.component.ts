import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { UserService } from '@shared/services/security/user.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';
import { ThfDialogService, ThfTableAction, ThfTableColumn } from '@totvs/thf-ui';
import { RoleService } from '@shared/services/security/role.service';
import { RoleDto } from '@shared/dtos/security/roles/role-dto';

@Component({
	selector: 'app-edit-user',
	templateUrl: './edit-user.component.html',
	styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

	user: UserDto;
	id: string;
	currentRoleId: number;
	roleColumns: Array<ThfTableColumn>;
	roleActions: Array<ThfTableAction>;

	@ViewChild('formEditUser') formEditUser: NgForm;

	constructor(
		private userService: UserService,
		private roleService: RoleService,
		private route: ActivatedRoute,
		private location: Location,
		private thfDialog: ThfDialogService,
	) { }

	ngOnInit() {
		this.id = this.route.snapshot.paramMap.get("id");
		this.userService.getUserById(this.id).subscribe(user => {
			this.user = user;
		})
		this.screenDesigner();
	}

	addRoleToTable() {
		if (this.currentRoleId && !this.user.roles.find(r => r.id == this.currentRoleId)) {

			this.roleService.getRoleById(this.currentRoleId).subscribe(role => {
				this.user.roles.push(role);
			})
			this.currentRoleId = undefined;
		}
	}

	screenDesigner() {
		this.roleColumns = this.getRolesColumns();
		this.roleActions = this.getRoleTableActions();
	}
	save() {
		if (this.user.getRoles().length) {
			this.userService.updateUser(this.user).subscribe(user => {
				this.thfDialog.alert({
					title: "Sucesso",
					message: "Usuário alterado com sucesso",
					ok: () => this.location.back()
				});
			});
		}
	}

	cancel() {
		if (!this.formEditUser.dirty) {
			this.location.back();
		} else {
			this.thfDialog.confirm({
				title: "Confirmar",
				message: "As alterações serão perdidas. Deseja continuar?",
				confirm: () => this.location.back()
			});
		}
	}

	getRolesColumns(): Array<ThfTableColumn> {
		return [
			{ property: 'id', label: 'ID' },
			{ property: 'role', label: 'Permissão' }
		];
	}

	getRoleTableActions(): Array<ThfTableAction> {
		return [
			{ action: this.removeRole.bind(this), icon: 'thf-icon-delete', label: 'Excluir' }
		];
	}

	removeRole(role: RoleDto) {
		var index: number = this.user.roles.indexOf(role, 0);
		if (index > -1) {
			this.user.roles.splice(index, 1);
		}
	}

}
