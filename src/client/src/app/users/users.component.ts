import { Component, OnInit } from '@angular/core';
import { ThfTableColumn, ThfTableAction, ThfPageAction, ThfDialogService } from '@totvs/thf-ui';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { UserService } from 'src/shared/services/security/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { TotvsResponse } from '@shared/dtos/base/totvs-response.interface';

@Component({
	selector: 'app-users',
	templateUrl: './users.component.html',
})
export class UsersComponent implements OnInit {
	userColumns: Array<ThfTableColumn> = new Array<ThfTableColumn>();
	userList: Array<UserDto> = new Array<UserDto>();
	tableActions: Array<ThfTableAction> = new Array<ThfTableAction>();
	pageActions: Array<ThfPageAction> = new Array<ThfPageAction>();

	constructor(private userService: UserService,
		private router: Router,
		private route: ActivatedRoute,
		private thfDialog: ThfDialogService
	) { }

	ngOnInit() {
		this.screenDesigner();
		this.userService.getUsers().subscribe((data: TotvsResponse<UserDto>) => {
			this.userList = data.items;
		})
	}

	private screenDesigner() {
		this.userColumns = this.getUsersColumns();
		this.tableActions = this.getTableActions();
		this.pageActions = this.getPageActions()
	}

	private getUsersColumns(): Array<ThfTableColumn> {
		return [
			{ property: 'id', label: 'ID' },
			{ property: 'mail', label: 'Email' },
			{ property: 'name', label: 'Nome' },
			{ property: 'username', label: 'Usuário' }
		];
	}

	private getTableActions(): Array<ThfTableAction> {
		return [
			{ action: this.detailUser.bind(this), icon: 'thf-icon-eye', label: 'Visualizar' },
			{ action: this.editUser.bind(this), icon: 'thf-icon-edit', label: 'Editar' },
			{ action: this.changeUserStatus.bind(this), icon: 'thf-icon-lock', label: 'Desabilitar' },
			{ action: this.deleteUser.bind(this), icon: 'thf-icon-delete', label: 'Excluir' }
		];
	}

	private getPageActions(): Array<ThfPageAction> {
		return [
			{ label: "Adicionar", action: this.createUser }
		]
	}

	editUser(user: UserDto): void {
		this.router.navigate(["./edit", user.id], { relativeTo: this.route })
	}

	deleteUser(user: UserDto): void {
		this.thfDialog.confirm({
			title: "Confirmar",
			message: `Confirma a exclusão do usuário ${user.username}?`,
			confirm : () => this.processDelete(user)
		})
	}

	processDelete(user: UserDto) {
		this.userService.deleteUser(user.id).subscribe(
			() => {
				this.removeUserFromList(user);
			},
			error => {
				this.thfDialog.alert({
					title: "Erro",
					message: "Não foi possível apagar o usuário " + error
				})
			}
		);
	}

	changeUserStatus(user: UserDto): void {

	}

	detailUser(user: UserDto): void {
		this.router.navigate(["./detail", user.id], { relativeTo: this.route })
	}

	createUser(): void {
		this.router.navigate(["./create"], { relativeTo: this.route })
	}

	removeUserFromList(user: UserDto) {
		var index: number = this.userList.indexOf(user, 0);
		if (index > -1) {
			this.userList.splice(index, 1);
		}
	}
}

