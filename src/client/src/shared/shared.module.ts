import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';

import { ThfModule, ThfPageModule, ThfTabsModule, ThfFieldModule } from '@totvs/thf-ui';
import { ThfTemplatesModule, ThfPageLoginModule  } from '@totvs/thf-templates';
import { ModuleWithProviders } from '@angular/compiler/src/core';
import { AuthGuard } from './auth/auth.guard';
import { ServicesModule } from './services/services.module';
import { AnonymousGuard } from './auth/anonymous.guard';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { EqualValidator } from './directives/equal.validator';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ThfModule,
    ThfTemplatesModule,
	ThfPageLoginModule,
	ThfPageModule,
	ServicesModule,
	ThfTabsModule,
	ThfFieldModule,
	ReactiveFormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ThfModule,
    ThfTemplatesModule,
	ThfPageLoginModule,
	ThfPageModule,
	EqualValidator,
	ThfTabsModule,
	ThfFieldModule,
	ReactiveFormsModule
  ],
  declarations : [
	LoginComponent, 
	NotFoundComponent,
	EqualValidator
	]
})
export class SharedModule { 
	static forRoot() : ModuleWithProviders {
		return { 
			ngModule  : SharedModule,
			providers :[
				AuthGuard,
				AnonymousGuard
			]
		} 
	}
}
