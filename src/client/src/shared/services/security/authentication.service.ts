import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDto } from '@shared/dtos/security/users/user-dto';
import { store } from 'src/shared/consts/current-user.const';
import { SecurityService } from 'src/shared/services/security/security.service';
import { Router } from '@angular/router';

/**
 * This service manage the Authentication
 */
@Injectable()
export class AuthenticationService {
    constructor(
        private securityService: SecurityService,
        private router: Router,
    ) { }

    /**
     * Get the logged user
     */
    public getUser(): Observable<UserDto> {
        return new Observable<UserDto>((ob: any) => {

            // Get JWT token from browser storage
            const token = sessionStorage.getItem('token') || localStorage.getItem('token');

            // Get user from store
            store.currentUser$.subscribe(user => {
                if (token && user) {
                    // UserDto logged and stored token
                    ob.next(user);
                } else if (token && !user) {
                    // If refresh page and have token but not logged user
                    // Verify token and get user
                    this.securityService.verifyToken(token).subscribe(
                        usr => {
                            if (!usr || (!usr.id && !usr.success)) {
                                ob.next(undefined);
                            } else {
                                ob.next(usr);
                            }
                        }
				);
                } else {
                    // Logged user
                    ob.next(user);
                }
            });
        });
    }

    /**
     * Logout function
     */
    logout() {
        // Clear token and remove user from local storage to log user logout
        localStorage.removeItem('token');
        sessionStorage.removeItem('token');
        store.setUser(null);
        this.router.navigate(['/login']);
    }


}