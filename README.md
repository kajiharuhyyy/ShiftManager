# ShiftManager— シフト管理ミニアプリ
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1.2-blue)
![H2 Database](https://img.shields.io/badge/H2-Database-lightgrey)
![JPA](https://img.shields.io/badge/Spring%20Data%20JPA-3.x-yellowgreen)
![Hibernate](https://img.shields.io/badge/Hibernate-6.x-red)
![Maven](https://img.shields.io/badge/Maven-3.9.0-C71A36)



## はじめに
ShiftManager は、社内メンバー向けのシンプルなタスク管理アプリです。
シフトの登録・編集・一覧表示ができ、担当者(Staff)と紐付けて管理できます。

Spring Boot + Thymeleaf + H2 Database の構成で動作する学習用 Web アプリです。

チャットGPTに上司として依頼メールを投げてもらい、それをもとに作成しました。

## 上司からの依頼メール

```bash
件名：シフト管理アプリ試作のお願い

梶野くん

お疲れ様です。
業務改善プロジェクトの一環として、簡易的なシフト管理アプリの試作をお願いしたく連絡しました。

現場から「日々のスタッフの勤務時間をすぐ確認したい」という声が上がっており、
まずは基本的な機能だけを備えたミニアプリを作成したいと考えています。

【依頼内容】
・スタッフ情報の登録／編集／削除
・シフト（勤務日、開始／終了時間、担当者、備考）の登録／編集／削除
・一覧画面でスタッフ名・勤務日などが確認できること
・シフト登録時はプルダウンでスタッフを選択可能にすること

【技術要件】
・Spring Boot（最新の安定版）
・Thymeleaf を使用した画面構築
・H2 Database を利用したデータ管理
・簡易的で構いませんが、社内でデモできるレベルのUI/UXを意識してください

まずは試作版を作成いただき、実際に動作するアプリとして確認できれば十分です。
完成したら一度見せてください。

よろしくお願いします。

（上司）
```

---

## 機能


### シフト管理
- シフト一覧表示
- シフト新規登録
- シフト編集
- 勤務時間（h）の管理
- 担当者（Staff）との紐付け

![タスク登録画面](./images/シフト登録.png)
![タスク一覧画面](./images/シフト一覧.png)

### スタッフ管理
- スタッフ一覧
- スタッフの新規登録
- スタッフ編集
- スタッフ削除

![メンバー一覧画面](./images/スタッフ一覧.png)
![メンバー登録画面](./images/スタッフ登録.png)

---

## 技術スタック

- **Spring Boot 3.5.8**
- **Thymeleaf**
- **H2 Database**
- **Spring Data JPA / Hibernate**
- **Maven**
- **Java17**

---

### セットアップ
#### 1 Clone

```bash
git clone https://github.com/yourname/ShiftManager.git
cd ShiftManager
```


#### 2 Run

``` bash
./mvnw spring-boot:run
```

#### 3 Access

``` bash
http://localhost:8080/shifts
```
---

## 学習したこと

- Spring MVC（Controller / Form / View）の一連の流れ
- CRUD 実装の基礎
- Bean Validation による入力チェック
- 多対一リレーション（Shift → Staff）
- H2 データ初期化（schema.sql / data.sql）
- Thymeleaf のフォームバインディング理解

---

## 今後の拡張予定

- ログイン・認可機能（Spring Security）
- シフト検索（スタッフ・期間など）
- 月ごとの勤務時間レポート
- API 化（React フロント版）
- デプロイ（Render / Railway / AWS）

## 作者
**梶野　悠久**


