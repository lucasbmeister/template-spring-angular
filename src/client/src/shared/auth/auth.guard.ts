import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthenticationService } from 'src/shared/services/security/authentication.service';
import { Observable } from 'rxjs';
import { UserDto } from '@shared/dtos/security/users/user-dto';

/**
 * This class intercept route change and check for security
 */
@Injectable()
export class AuthGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) { }

    /**
     * Check routes permission
     */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        // Get authorized roles for route
        const roles = route.data["permissions"];
       // Object.keys(route.data).forEach(key => roles.push(route.data[key]));

        // Return observable
        return new Observable<boolean>((ob: any) => {
            // Get logged user
            this.authenticationService.getUser().subscribe((user : UserDto) => {
                if (!user) {
                    // Not logged
                    ob.next(false);
                    this.router.navigate(['/login']);
				} else {
                    // Logged user
                    const userObj: UserDto = new UserDto(user.id, user.username, user.token, user.roles);
                    if (roles && roles.length > 0) {
                        // Check roles
                        if (userObj.hasRole(roles)) {
                            ob.next(true);
                        } else {
                            ob.next(false);
                            this.router.navigate(['/login']);
                        }
                    }
                    ob.next(true);
                }
            });
        });
    }

}
