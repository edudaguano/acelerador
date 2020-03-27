import { Component } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { EncryptionService, ChangeKeysResponse } from '@afe/encryption';
import { MBSGroups } from './models/mbs-groups.model';
import { urlConfig } from './config/url.config';
{$import_components$}


{$component_ts$}

export class {$name_FirstLetterUp$}Component implements OnInit {
    {$global_variables$}

    constructor() {
        {$service_variables_initialization$}
    }

    ngOnInit() {
        {$content_outside_functions$}
    }


    $functions$

}

/*
title = 'Aplicacao de Referencia';

  dataEncrypted : string;
  decryptResult: string;
  dataToEncrypt: string;

  constructor(
    private httpClient: HttpClient,
    private encryptionService: EncryptionService
  ) {}

  public ngOnInit(): void {
   this.httpClient.get(urlConfig.sso).subscribe( (data) => {
     console.log(data);

      this.encryptionService.changeKeys().subscribe((response: ChangeKeysResponse) => {
        console.log(response);
      },

      (err) => console.log("Error: "+ err ));
   });
  }

*/
