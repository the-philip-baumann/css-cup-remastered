export class AuthState {

  ok: boolean
  jwt: string
  error: string

  private constructor(ok: boolean, jwt?: string, error?: string) {
    this.ok = ok
    this.jwt = jwt
    this.error = error
  }

  public static success(jwt: string): AuthState {
    return new AuthState(true, jwt)
  }

  public static failure(error?: string): AuthState {
    return new AuthState(false, null, error)
  }

}
