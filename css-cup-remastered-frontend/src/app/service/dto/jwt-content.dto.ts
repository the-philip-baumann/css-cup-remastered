export interface JwtContentDto {
  token: string;
  verified: boolean;
  exp: number;
}
