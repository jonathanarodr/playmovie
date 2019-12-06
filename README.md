[![Build Status](https://travis-ci.com/jonathanarodr/playmovie.svg?branch=master)](https://travis-ci.com/jonathanarodr/playmovie)
[![CircleCI](https://circleci.com/gh/jonathanarodr/playmovie/tree/master.svg?style=shield)](https://circleci.com/gh/jonathanarodr/playmovie/tree/master)
[![codecov](https://codecov.io/gh/jonathanarodr/playmovie/branch/master/graph/badge.svg)](https://codecov.io/gh/jonathanarodr/playmovie)
[![codebeat](https://codebeat.co/badges/23b02036-2aaf-4325-a856-add0e46d6832)](https://codebeat.co/projects/github-com-jonathanarodr-playmovie-master)
[![maintainability](https://api.codeclimate.com/v1/badges/fb72b670ebacdae2311c/maintainability)](https://codeclimate.com/github/jonathanarodr/playmovie/maintainability)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/817dc26995a949888e82adacd5d218bd)](https://www.codacy.com/manual/jonathanarodr/playmovie?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jonathanarodr/playmovie&amp;utm_campaign=Badge_Grade)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

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
