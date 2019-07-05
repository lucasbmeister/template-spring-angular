import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './home/home.component';
import { SharedModule } from 'src/shared/shared.module';
import { ServicesModule } from 'src/shared/services/services.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    AppRoutingModule,
	SharedModule,
	ServicesModule
  ]
})
export class AppModule { }
