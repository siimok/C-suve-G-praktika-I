export interface Movie {
  id: number,
  name: string,
  rating: number,
  published: number,
  minimumAge: number,
  language: 'ESTONIAN' | 'ENGLISH' | 'RUSSIAN' | 'FRENCH',
  description: string,
  imageUrl: string,
  genres: Array<Genre>,
  sessions: Array<Session>,
}

export interface Session {
  id: number,
  movieId: number,
  startTime: string,
  tickets: Array<Ticket>,
}

export interface Ticket {
  id?: number,
  sessionId: number,
  rowNumber: number,
  seatNumber: number,
}

export interface Genre {
  id: number,
  name: string
}
