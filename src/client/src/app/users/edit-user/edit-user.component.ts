import { Component, OnInit, ViewChild } from '@angular/core';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { UserService } from '@shared/services/security/user.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { Location } from '@angular/common';

@Component({
	selector: 'app-edit-user',
	templateUrl: './edit-user.component.html',
	styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

	user: UserDto;
	id: string;

	@ViewChild('formEditUser') formEditUser: NgForm;

	constructor(
		private userService: UserService,
		private route: ActivatedRoute,
		private location : Location
		) { }

	ngOnInit() {
		this.id = this.route.snapshot.paramMap.get("id");
		this.userService.getUserById(this.id).subscribe(user => {
			this.user = user;
		})
	}

	save() {

	}

	cancel() {
		if (!this.formEditUser.dirty) {
			this.location.back();
		} else {

		}

	}

}
