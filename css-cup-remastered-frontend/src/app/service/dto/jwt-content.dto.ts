export interface JwtContentDto {
  id: number
  firstname: string
  lastname: string
  function: string
  discipline: string
  email: string
  token: string
  verified: boolean
  exp: number;
  role: string
}
