import { Component, OnInit } from '@angular/core';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { SecurityService } from 'src/shared/services/security/security.service';
import { Router } from '@angular/router';
import { store } from 'src/shared/consts/current-user.const';
import { ThfPageLogin } from '@totvs/thf-templates';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  	user: UserDto = new UserDto(null, null, null, null);

    showError: boolean;
    remember: boolean;

    constructor(
        private securityService: SecurityService,
        private router: Router
    ) {}

    login(form: ThfPageLogin) {
        if (form.login && form.password) {

			this.user.setUsername(form.login);
			this.user.setPassword(form.password);

			const sha3pass = this.user.passwordToSHA3();

            this.securityService.login(this.user.username, sha3pass, form.rememberUser === undefined ? false : form.rememberUser)
            .subscribe(
                user => {
                    this.showError = false;
                    this.router.navigate(['/app/home']);
                    this.setUser(user);
                },
                err => {
					this.showError = true
					console.log(err)
				}
            );
        }
    }
    private setUser(user: UserDto) {
        store.setUser(user);
    }
}
