# Sistema de gestão para salão de beleza

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/LICENSE) 

# Sobre o projeto

Este projeto é uma aplicação full stack desenvolvida com o objetivo de otimizar a gestão de salões de beleza, centralizando processos essenciais do negócio em uma única plataforma. O sistema oferece funcionalidades como gerenciamento de atendimentos, controle financeiro, cadastro de clientes, serviços, profissionais e agendamentos, proporcionando mais organização, produtividade e eficiência operacional.

## Layout mobile
![Mobile 1](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/project-assets/Captura%20de%20tela%202026-06-09%20111607.png) ![Mobile 2](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/project-assets/Captura%20de%20tela%202026-06-09%20111850.png)

## Layout web
![Web 1](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/project-assets/Captura%20de%20tela%202026-06-09%20122236.png)

![Web 2](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/project-assets/Captura%20de%20tela%202026-06-09%20125815.png)

## Modelo nas tabelas no banco de dados
![Modelo Conceitual](https://github.com/cauerg44/beauty-ledger-platform/blob/feat/docker/project-assets/Tabelas%20do%20banco%20de%20dados.png)

# Tecnologias Utilizadas

### Back-end

O back-end foi desenvolvido com **Java 21** e **Spring Boot**, seguindo os princípios de uma arquitetura baseada em **API REST**. A aplicação centraliza as regras de negócio do sistema, implementando autenticação e autorização com **Spring Security** e **JWT**, persistência de dados com **Spring Data JPA/Hibernate** e versionamento do banco de dados por meio do **Flyway Migrations**. O gerenciamento de dependências e o processo de build são realizados com **Maven**. A API possui documentação interativa gerada com **Swagger/OpenAPI**, permitindo visualizar e testar os endpoints disponíveis diretamente pelo navegador.

<div align="left">
  <img src="https://skillicons.dev/icons?i=java,spring,maven"/>
</div>

### Front-end

O front-end foi desenvolvido com **React.js** e **TypeScript**, adotando uma arquitetura baseada em componentes que favorece a reutilização de código, escalabilidade e manutenção da aplicação. O gerenciamento de rotas é realizado com **React Router DOM**, enquanto a comunicação com a API REST ocorre por meio do **Axios**. A interface foi construída com **HTML5** e **CSS3**, priorizando responsividade, usabilidade e uma experiência intuitiva para os usuários.

<div align="left">
  <img src="https://skillicons.dev/icons?i=react,typescript,html,css,yarn"/>
</div>

### Banco de Dados

O sistema utiliza **MySQL** como banco de dados relacional para armazenamento e gerenciamento das informações da aplicação.

<div align="left">
  <img src="https://skillicons.dev/icons?i=mysql"/>
</div>

### Controle de Versão

O versionamento do código-fonte é realizado com **Git**, utilizando o **GitHub** para hospedagem, colaboração e gerenciamento do repositório.

<div align="left">
  <img src="https://skillicons.dev/icons?i=git,github"/>
</div>

### Containerização

A aplicação pode ser executada em ambiente isolado utilizando **Docker**, garantindo maior portabilidade, consistência entre ambientes e facilidade no processo de implantação.

<div align="left">
  <img src="https://skillicons.dev/icons?i=docker"/>
</div>

### Ferramentas de Desenvolvimento

Durante o desenvolvimento foram utilizados **IntelliJ IDEA** e **Visual Studio Code**.

<div align="left">
  <img src="https://skillicons.dev/icons?i=idea,vscode"/>
</div>

# Desenvolvedor

_**Cauê da Rocha Garcia**_

<div align="left">
  <a href="https://www.linkedin.com/in/cauegarcia8112004/" target="_blank">
    <img src="https://skillicons.dev/icons?i=linkedin,github" alt="Redes"/>
  </a>
</div>
