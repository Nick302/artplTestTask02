# artplTestTask02
artplTestTask02 - test task
Буду развивать дальше как пет проект и добавлю JWT + HATEOAS + тесты
<hr>
<br>
Methods for create users
<br>
POST = http://localhost:8088/api/v1/auth/registration

```
{
         "name":"admin",
         "email": "nchernov@gmail.com",
         "password":"123456" 
}
```

POST = http://localhost:8088/api/v1/auth/login<br>

```
{
         "email": "nchernov@gmail.com",
         "password":"123456" 
}
```

GET now session = http://localhost:8088/api/v1/auth/current <br>
GET BY ID = http://localhost:8088/api/v1/user/1<br>
GET BY email = http://localhost:8088/api/v1/auth/check/email<br>
Logout = http://localhost:8088/api/v1/auth/logout<br>
DELETE = http://localhost:8088/api/v1/user/1<br>
PUT = http://localhost:8088/api/v1/user/1<br>
```
[
    {
        "id": 1,
        "name": "s.302",
        "email": "s.302",
        "password": "$2a$10$MoMVQx4qHn3.OnNH46yaHu0.dEqZ8nv2NGjfkY160Yn7war2/.wja",
        "role": "USER",
        "locked": false,
        "enabled": true,
        "pets": [],
        "username": "s.302",
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "authorities": [
            {
                "authority": "USER"
            }
        ],
        "accountNonLocked": true
    }
]
```

<hr>
PETS

POST = http://localhost:8088/api/v1/pets
```
{
    "id": 3,
    "nickName": "jack",
    "birthDay": "2022-07-07 10:10",
    "sex": "BOY",
    "app_user": 1
  }
```
PUT = http://localhost:8088/api/v1/pets/3
```
 {
    "nickName": "jack",
    "birthDay": "2022-07-07 10:10",
    "sex": "BOY",
    "app_user": {
         "id": 1
    }
  }
  ```
GET = http://localhost:8088/api/v1/pets<br>
GET BY ID = http://localhost:8088/api/v1/pets/3<br>
Delete = http://localhost:8088/api/v1/pets/1<br>
