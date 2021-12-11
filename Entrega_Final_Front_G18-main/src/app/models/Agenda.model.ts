export interface Agenda {
    id:             string;
    usuario:        Usuario;
    fecha:          Date;
    nombre:         string;
    servicio:       Stecnico;
      
}

export interface Stecnico {
    id?:     string;
    nombre: string;
}

export interface Usuario {
    id:       string;
    nombre:   string;
    correo:   string;
    username: string;
    password?: string;
    hash?:     string;
}