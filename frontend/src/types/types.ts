export interface Movie {
  id: number,
  name: string,
  rating: number,
  published: number,
  minimumAge: number,
  language: 'ESTONIAN' | 'ENGLISH' | 'RUSSIAN' | 'FRENCH',
  description: string,
  genre: Array<Genre>,
  sessions: null,
}

interface Genre {
  id: number,
  name: string
}
