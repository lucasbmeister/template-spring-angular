import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { UserService } from '@shared/services/security/user.service';
import { ThfDialogService, ThfTableColumn, ThfTableAction } from '@totvs/thf-ui';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { NgForm } from '@angular/forms';
import { RoleDto } from '@shared/dtos/security/roles/role-dto';
import { RoleService } from '@shared/services/security/role.service';

@Component({
	selector: 'app-create-user',
	templateUrl: './create-user.component.html',
	styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

	user: UserDto = new UserDto();
	submitted: boolean = false;
	roleColumns: Array<ThfTableColumn>;
	roleActions: Array<ThfTableAction>;
	currentRoleId: number;

	@ViewChild('formCreateUser') formCreateUser: NgForm;

	constructor(
		private userService: UserService,
		private roleService: RoleService,
		private router: Router,
		private location: Location,
		private thfDialog: ThfDialogService,
		private route : ActivatedRoute
	) { }

	ngOnInit() {
		this.user.setRoles(new Array<RoleDto>());
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
		this.processSave();
	}

	saveNew() {
		this.processSave(true);
	}

	processSave(andNew?: boolean) {
		this.userService.createUser(this.user).subscribe(
			(user) => {
				this.thfDialog.alert({
					title: "Sucesso",
					message: "Usuário criado com sucesso!",
					ok: () => andNew ? this.router.navigate(["./create"], {relativeTo : this.route}) : this.location.back()
				});
			},
			error => {
				this.thfDialog.alert({
					title: "Erro",
					message: "Erro ao criar usuário" + error
				});
			}

		);
	}

	cancel() {

		if (!this.formCreateUser.dirty) {
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
