import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const jwtHelper = new JwtHelperService();

  const token = sessionStorage.getItem('jwtToken');

  if (!token) {
    router.navigate(['/login']);
    return false;
  }

  if (jwtHelper.isTokenExpired(token)) {
    sessionStorage.removeItem('jwtToken');
    router.navigate(['/login']);
    return false;
  }

  const decodedToken: any = jwtHelper.decodeToken(token);
  const userRole = decodedToken?.role || sessionStorage.getItem('uloga');
  const requiredRoles = route.data?.['roles'] as string[] | undefined;

  if (requiredRoles && !requiredRoles.includes(userRole)) {
    router.navigate(['/home']);
    return false;
  }

  return true;
};