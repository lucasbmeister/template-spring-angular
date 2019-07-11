import { Directive, forwardRef, Attribute } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';

@Directive({
	selector:
		// tslint:disable-next-line:directive-selector
		'[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
	providers: [
		{
			provide: NG_VALIDATORS,
			useExisting: forwardRef(() => EqualValidator),
			multi: true
		}
	]
})
export class EqualValidator implements Validator {
	constructor(
		@Attribute('validateEqual') public validateEqual: string,
		@Attribute('reverse') public reverse: string
	) { }

	private get isReverse() {
		if (!this.reverse) {
			return false;
		}
		return this.reverse === 'true' ? true : false;
	}

	validate(control: AbstractControl): { [key: string]: any } {
		// self value
		const value = control.value;

		// second control
		const control2 = control.root.get(this.validateEqual);

		if (control2 && control2.value === null)
			control2.setValue("");

		if (control.errors && !control.errors.validateEqual && (control2.value === null || control2.value === "")) {
			if(control.value == control2.value){
				this.deleteValidateError(control2);
			}
			return;
		}
		// value equal
		if (control2 && value === control2.value && !this.isReverse) {
			if (control2.errors) {
				this.deleteValidateError(control2)
			}
			if (control.errors) {
				this.deleteValidateError(control)
			}
			return null;
		}

		// value not equal
		if (control2 && value !== control2.value && !this.isReverse) {
			return {
				validateEqual: { value: false }
			};
		}

		// value equal and reverse
		if (control2 && value === control2.value && this.isReverse) {
			if (control2.errors) {
				this.deleteValidateError(control2)
			}
			if (control.errors) {
				this.deleteValidateError(control)
			}
			return null
		}

		// value not equal and reverse
		if (control2 && value !== control2.value && this.isReverse) {
			control2.setErrors({ validateEqual: { value: false } });
		}

		return null;
	}

	deleteValidateError(control: AbstractControl) {
		delete control.errors['validateEqual'];
		if (!Object.keys(control.errors).length) {
			control.setErrors(null);
		}
	}
}
