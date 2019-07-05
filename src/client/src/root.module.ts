import { NgModule } from "@angular/core";
import { RootComponent } from "./root.component";
import { BrowserModule } from "@angular/platform-browser";
import { SharedModule } from "@shared/shared.module";
import { RootRoutingModule } from "./root-routing.module";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { AuthInterceptor } from "@shared/auth/auth.interceptor";

@NgModule({
	imports : [
		BrowserModule,
		SharedModule.forRoot(),
		RootRoutingModule
	],
	providers : [
		{provide : HTTP_INTERCEPTORS, useClass : AuthInterceptor, multi : true}
	],
	declarations : [RootComponent],
	bootstrap : [RootComponent]
})

export class RootModule {
	
}