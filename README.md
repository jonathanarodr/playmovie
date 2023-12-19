# Playmovie

[![Developer CI](https://github.com/jonathanarodr/playmovie/actions/workflows/main.yml/badge.svg)](https://github.com/jonathanarodr/playmovie/actions/workflows/main.yml)
[![A11y CI](https://github.com/jonathanarodr/playmovie/actions/workflows/a11y.yml/badge.svg)](https://github.com/jonathanarodr/playmovie/actions/workflows/a11y.yml)
[![CodeQL CI](https://github.com/jonathanarodr/playmovie/actions/workflows/github-code-scanning/codeql/badge.svg)](https://github.com/jonathanarodr/playmovie/actions/workflows/github-code-scanning/codeql)
[![Build Deployment](https://github.com/jonathanarodr/playmovie/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/jonathanarodr/playmovie/actions/workflows/pages/pages-build-deployment)

## About

Este projeto foi desenvolvido com intuito de aplicar meus conhecimentos em relação ao desenvolvimento Android e padrões de projeto.

## Overview

Playmovie utiliza como fonte de dados a API [api.themoviedb.org](https://api.themoviedb.org) para exibição de filmes e séries mais populares na atualidade.

## Getting Started

Acesse a sessão de API disponível no [thememoviedb](https://www.themoviedb.org/settings/api) e copie o token de leitura da API **(v4 auth)**.

Após captura do token, acesse o arquivo `build.gradle.kts` do módulo `:network` e configure a propriedade `AUTHORIZATION_KEY` informando sua chave de autorização:
  
```kotlin
buildConfigField("String", "AUTHORIZATION_KEY", "abc1.def2.ghi3")
```

> [!IMPORTANT]
> Para utilizar a API do thememoviedb, é necessário obter a chave de acesso *free* para
> desenvolvedores. Veja mais informações
> na [documentação themoviedb](https://developers.themoviedb.org/3/getting-started/introduction)

## Screenshots

![List of movies](.screenshots/screen_movies.png "A list of popular movies")
![Movie details](.screenshots/screen_detail.png "Details about the movie")

## Running coverage

```bash
./gradlew koverHtmlReportDebug
```

## Libraries

// TODO

## Credits

* [Thememoviedb](https://www.themoviedb.org/): fonte de dados sobre os filmes
* [Google Fonts](https://fonts.google.com/icons): iconografia do app
