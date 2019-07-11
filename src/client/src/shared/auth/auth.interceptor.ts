import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * This interceptor get token from sessionStorage if it is set and put the JWT token in the header Authorization var
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // Get token
        const token = sessionStorage.getItem('token') || localStorage.getItem('token');
		let headers = req.headers.append('Content-Type', 'application/json');
		headers = req.headers.append('Authorization', 'Bearer ' + token);

        if (token) {
            const cloned = req.clone({
                headers: headers
            });
            return next.handle(cloned);
        } else {
            return next.handle(req);
        }
    }
}
