
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); 

    // Reset error messages
    document.querySelector('.error-name').innerHTML = '';
    document.querySelector('.error-surname').innerHTML = '';
    document.querySelector('.error-email').innerHTML = '';
    document.querySelector('.error-password').innerHTML = '';
    document.querySelector('.error-confirm-password').innerHTML = '';

    var name = document.getElementById('name').value;
    var surname = document.getElementById('surname').value;
    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirm-password').value;


    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var passwordRegex = /^(?=.*[0-9])[a-zA-Z0-9]{6,}$/;


    if (!name.trim()) {
        document.querySelector('.error-name').innerHTML = 'Name is required';
        return false; 
    }


    if (!surname.trim()) {
        document.querySelector('.error-surname').innerHTML = 'Surname is required';
        return; 
    }


    if (!email.trim()) {
        document.querySelector('.error-email').innerHTML = 'Email is required';
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
        Swal.fire({
            icon: "infp",
            title: "",
            text:"Password must contain at least 6 characters and include at least 1 number."
          });
         return; 
    }

    if (!confirmPassword.trim()) {
        document.querySelector('.error-confirm-password').innerHTML = 'Confirm Password is required';
        return; 
    } else if (password !== confirmPassword) {
        document.querySelector('.error-confirm-password').innerHTML = 'Passwords do not match';
        return; 
    }


    var user = {
        name: name,
        surname: surname,
        email: email,
        password: password
    };

     sendData(user);
});



function sendData(user){
    $.ajax({
      type:"POST",
      url:"http://localhost:8080/user",
      data:JSON.stringify(user), 
      contentType: "application/json",
      success: function(data){
          Swal.fire({
            icon: "success",
            title: "Success!",
            text:data
          });
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
