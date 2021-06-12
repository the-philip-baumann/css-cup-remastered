export class PlayerCredentialsDto {


  constructor(role: string, id?: number, firstname?: string, lastname?: string, email?: string, func?: string, discipline?: string) {
    this.role = role;
    this.id = id
    this.firstname = firstname
    this.lastname = lastname
    this.email = email
    this.function = func
    this.discipline = discipline
  }

  id: number
  firstname: string
  lastname: string
  email: string
  function: string
  discipline: string
  role: string;

  hasAuthority(...authorities: string[]) {
    if (authorities.indexOf('ROLE_UNDECIDED') != -1) {
      return true
    }
    // TODO: Remove
    return true
  }

}
