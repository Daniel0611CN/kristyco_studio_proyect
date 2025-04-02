import { Component, Input } from '@angular/core';
import { Invitacion } from '../../models/enums/rol.enum';

@Component({
  selector: 'app-invitacion',
  imports: [],
  templateUrl: './invitacion.component.html',
  styleUrl: './invitacion.component.css'
})
export class InvitacionComponent {

  invitacion_alas_de_cristal: Invitacion[] = [

  ]

  invitacion_brisa_de_fiesta: Invitacion[] = [

  ]

  invitacion_caricia_de_pluma: Invitacion[] = [

  ]

  invitacion_sueños_al_viento: Invitacion[] = [

  ]

  invitacion_susurro_de_mariposa: Invitacion[] = [

  ]

  invitacion_vuelo_de_amor: Invitacion[] = [

  ]

  invitaciones: {invitacion: Invitacion[], tipoInvitacion: string}[] = [
    { invitacion: this.invitacion_alas_de_cristal, tipoInvitacion: 'doble' },
    { invitacion: this.invitacion_brisa_de_fiesta, tipoInvitacion: 'doble' },
    { invitacion: this.invitacion_caricia_de_pluma, tipoInvitacion: 'doble' },
    { invitacion: this.invitacion_sueños_al_viento, tipoInvitacion: 'doble' },
    { invitacion: this.invitacion_susurro_de_mariposa, tipoInvitacion: 'doble' },
    { invitacion: this.invitacion_vuelo_de_amor, tipoInvitacion: 'doble' }
  ]

}
