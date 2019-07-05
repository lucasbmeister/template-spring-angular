import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';
import { AuthenticationService } from 'src/shared/services/security/authentication.service';
import { Observable } from 'rxjs';

@Injectable()
export class AnonymousGuard implements CanActivate {
    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) { }

    /**
     * Check routes permission
     */
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

        // Return observable
        return new Observable<boolean>((ob: any) => {
            // Get logged user
            this.authenticationService.getUser().subscribe((user) => {
                if (!user) {
                    // Not logged
                    ob.next(true);
                    //this.router.navigate(['/account/login']);
				} else {
                    ob.next(false);
                    this.router.navigate(['/app/home']);
                }
    	        ob.next(true);
            });
        });
    }

}
