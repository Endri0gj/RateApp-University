window.addEventListener("load",start,false);
function start(){
    var user=JSON.parse(localStorage.getItem("logedInUser"));
    var mainRef=document.getElementById("main-wrap");
       mainRef.innerHTML=`
        <div class="product">
            <div class="image-gallery">
                <img src="img/people/user_avatar.png"  id="productImg" alt="">
            </div>
            <div class="course-details2">
                <div class="details2" style="margin-top:90px"> 
                    <h3>Name:`+user.name +`</h2>
                    <h3>Surname:`+user.surname +`</h2>
                    <h3>Email:`+user.email +`</h2>    
					<button class="green" style="margin-left:40px; margin-top:10px" onClick="logout()">Logout </button>
                </div>
            </div>
        </div>`
}

function logout(){


    Swal.fire({
        title: "Do you want to logout?",
        showDenyButton: false,
        showCancelButton: true,
        confirmButtonText: "Logout"
      }).then((result) => {
        if (result.isConfirmed) {
            localStorage.clear();
            window.location.href="login.html";
        }
      });

}