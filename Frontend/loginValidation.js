document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    document.querySelector('.error-email').innerHTML = '';
    document.querySelector('.error-password').innerHTML = '';

    var emailRef= document.getElementById('email');
    var passwordRef= document.getElementById('password');
    var email = emailRef.value;
    var password =passwordRef.value;

    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 
    var passwordRegex = /^(?=.*[0-9])[a-zA-Z0-9]{6,}$/;


    if (!email.trim()) {
        document.querySelector('.error-email').innerHTML = 'Email is required!';
        return; 
    } else if (!emailRegex.test(email)) {
        document.querySelector('.error-email').innerHTML = 'Enter a valid email';
        return; 
    }


    if (!password.trim()) {
        document.querySelector('.error-password').innerHTML = 'Password is required';
        return; 
    }
     else if (!passwordRegex.test(password)) {
        document.querySelector('.error-password').innerHTML = 'Password must contain at least 6 characters and include at least 1 number.';
        return; 
    }


    if (emailRegex.test(email) && passwordRegex.test(password)) {
        var user = {
            email: email,
            password: password
        };
        sendData(user)
    }
});



function sendData(user){
    console.log("sads");
    $.ajax({
      type:"POST",
      url:"http://localhost:8080/user/login",
      data:JSON.stringify(user), 
      contentType: "application/json",
      success: function(data){
        localStorage.setItem("logedInUser",JSON.stringify(data));
        window.location.href = 'courses.html';
      },
      error: function(err){
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: err.responseText
          });
      }
  
    });
  }

