[![Build Status](https://travis-ci.com/jonathanarodr/playmovie.svg?branch=master)](https://travis-ci.com/jonathanarodr/playmovie)
[![CircleCI](https://circleci.com/gh/jonathanarodr/playmovie/tree/master.svg?style=shield)](https://circleci.com/gh/jonathanarodr/playmovie/tree/master)

# playmovie

Para configurar a chave de consulta da [API](https://www.themoviedb.org/), acesse o arquivo `build.gradle` e informe sua chave no field **API_KEY**

```java
...
//exemplo de configuração da API KEY:
buildConfigField("String" , "API_KEY", '"1234567890"')
...
```

## Screenshots

![List of popular movies](screenshots/popular_movie.png "A list of popular movies")
![List of favorite movies](screenshots/favorite_movie.png "A list of favorite movies")
![Movie details](screenshots/detail_movie.png "Details for a specific movie")
