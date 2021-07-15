# Playmovie

[![Workflow Status](https://github.com/jonathanarodr/playmovie/actions/workflows/android.yml/badge.svg)](https://github.com/jonathanarodr/playmovie/actions/workflows/android.yml)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![codecov](https://codecov.io/gh/jonathanarodr/playmovie/branch/master/graph/badge.svg)](https://codecov.io/gh/jonathanarodr/playmovie)
[![codebeat](https://codebeat.co/badges/23b02036-2aaf-4325-a856-add0e46d6832)](https://codebeat.co/projects/github-com-jonathanarodr-playmovie-master)
[![maintainability](https://api.codeclimate.com/v1/badges/fb72b670ebacdae2311c/maintainability)](https://codeclimate.com/github/jonathanarodr/playmovie/maintainability)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/817dc26995a949888e82adacd5d218bd)](https://www.codacy.com/manual/jonathanarodr/playmovie?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jonathanarodr/playmovie&amp;utm_campaign=Badge_Grade)

## About

Este projeto foi desenvolvido com intuito de aplicar meus conhecimentos em relação ao desenvolvimento Android e padrões de projeto.

## Overview

Playmovie utiliza como fonte de dados a API [api.themoviedb.org](https://api.themoviedb.org) para exibição de filmes e séries mais populares na atualidade.

## Getting Started

Acesse a sessão de API disponível no [thememoviedb](https://www.themoviedb.org/settings/api) e copie o token de leitura da API **(v4 auth)**.

Após captura do token, acesse o arquivo `build.gradle` e configure a propriedade `AUTHORIZATION_KEY` informando sua chave de autorização:
  
```groovy
buildConfigField("String", "AUTHORIZATION_KEY", '"abc1.def2.ghi3"')
```

> Para utilizar a API do thememoviedb, é necessário obter a chave de acesso *free* para desenvolvedores. Veja mais informações na [documentação themoviedb](https://developers.themoviedb.org/3/getting-started/introduction)

## Screenshots

![List of movies](screenshots/screen_movies.png "A list of popular movies")
![List of series](screenshots/screen_series.png "A list of popular tv series")

## Libraries

## Credits

* [Thememoviedb](https://www.themoviedb.org/): fonte de dados sobre os filmes
* [Google Fonts](https://fonts.google.com/icons): iconografia do app
