import { Component, } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
name:String="";
pass:String="";
mail:String="";
toggle:boolean=false;
// static jwt:String="";


constructor(private router:Router){}
Singin(){
  console.log(this.name);
  console.log(this.pass);
  console.log(this.mail);
  fetch('http://localhost:8080/signin', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include',
    body: JSON.stringify({
      name: this.name,
      mail: this.mail,
      pass: this.pass
    })
  })
  .then(response => response.json())
  .then(data => {
    console.log(data);
    if(data.msg){
        this.router.navigate(['home'],{state:{jwt: data.access_token}});
    }
  })
  .catch(error => {
    console.error(error);
  });
}

Signup(){
  console.log(this.name);
  console.log(this.pass);
  console.log(this.mail);
  fetch('http://localhost:8080/signup', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      name: this.name,
      mail: this.mail,
      pass: this.pass
    })
  })
  .then(response => response.json())
  .then(data => {
    console.log(data);
  })
  .catch(error => {
    console.error(error);
  });
}
}
