# 書籍管理システム

## 概要
『Kotlinサーバーサイドプログラミング実践開発』の書籍管理システム

## 機能一覧
- ログイン・セッション管理
- 権限管理
- 書籍一覧取得
- 書籍詳細取得
- 書籍情報登録
- 書籍情報更新
- 書籍情報削除
- 貸し出し
- 返却

## 技術スタック

### Language
Kotlin

### Framework
Spring Boot

### Frontend
Vue.js

### RDB
MySQL

### ORM
MyBatis

## Architecture

Onion Architectureを参考にする。

| 階層 | 概要 |
| --- | --- |
| UserInterface | Presentation層とも言われる。UIに直結する処理の実装を担う |
| Infrastructure | I/Oに関する技術スタックに関する実装を担う |
| Application Service | 各機能の仕様に応じた処理(UseCase)を担う |
| Domain Service |ドメインに関するビジネスロジックの実装を担う |
| Domain Model |ドメインに関する状態と振る舞いの実装を担う |
| Tests | テストコードの実装を担う |

本システムでは以下とする。

| 階層 | パッケージ | 主なコード |
| --- | --- | --- |
| Presentation(UI) | com.book.manager.presentation | Controller class, Form class |
| Infrastructure | com.book.manager.infrastructure | RepositoryImpl class |
| Application Service | com.book.manager.application | Service class |
| Domain | com.book.manager.domain | Repository interface, Model class |
