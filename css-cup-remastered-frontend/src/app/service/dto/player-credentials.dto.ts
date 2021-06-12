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
    console.log('ROLE', this.role)
    console.log('ROLES', authorities[0])
    console.log('comp', authorities[0] == this.role)
    console.log('com2', authorities.indexOf("ROLE_UNDECIDED"))
    if (authorities.indexOf('ROLE_UNDECIDED') != -1) {
      console.log('result')
      return true
    }
    return false
  }

}
