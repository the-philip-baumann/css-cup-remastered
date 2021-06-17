import {Inject, Injectable, InjectionToken} from "@angular/core";

export const CSS_CUP_STORAGE_SERVICE =
  new InjectionToken<StorageService>('CSS_CUP_STORAGE_SERVICE');

@Injectable({
  providedIn: "root"
})
export class StorageService {

  constructor(@Inject(CSS_CUP_STORAGE_SERVICE) private storage: StorageService) {

  }

}
