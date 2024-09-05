
window.addEventListener("load",start,false);
function start(){
    var course=JSON.parse(localStorage.getItem("selectedCourse"));
    var mainRef=document.getElementById("main-wrap");
    var buttons;
    if(course.userRegistered==false){
        buttons=`<button class="green" onClick="register()" >Register </button>`;
    }else{
        buttons=`<button class="disable" >Register </button>
        <button class="normal" style="margin-left:10px" onClick="unregister()">Unregister </button>`;
    }
    mainRef.innerHTML=`
        <div class="product">
            <div class="image-gallery">
                <img src="img/courses/`+course.name +`.png"  id="productImg" alt="">
            </div>
            <div class="course-details2">
                <div class="details2"> 
                    <h2>`+course.name +`</h2>
                    <h3>`+course.weekDay +` `+course.startTime+`-`+course.endTime+`</h3>
                    <h4>Prof.`+course.professor +`</h4>
                    <p>From crafting sophisticated
                        algorithms to architecting robust systems, the domain of `+course.name +` embraces a spectrum of disciplines, driving efficiencies,
                        connectivity, and transformative experiences.In this field possibilities are limitless.
                    </p>
                    <p>Registered students: `+course.registeredStudents +`</p>      
					`+ buttons +`
                </div>
            </div>
        </div>`

        getComments(course.id);
}

function register(){
    var course=JSON.parse(localStorage.getItem("selectedCourse"));
    var user=JSON.parse(localStorage.getItem("logedInUser"));
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/courses/insert?courseId="+course.id+"&userId="+user.id, 
        success: function(data){
            course.userRegistered=true;
            var regStudents=parseInt(course.registeredStudents);
            regStudents+=1;
            course.registeredStudents=regStudents;
            localStorage.setItem("selectedCourse",JSON.stringify(course));
            start();
        },
        error: function(err){
          console.log(err);
        }
    
      });
}

function unregister(){
    var course=JSON.parse(localStorage.getItem("selectedCourse"));
    var user=JSON.parse(localStorage.getItem("logedInUser"));
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/courses/delete?courseId="+course.id+"&userId="+user.id, 
        success: function(data){ 
            course.userRegistered=false;
            var regStudents=parseInt(course.registeredStudents);
            regStudents-=1;
            course.registeredStudents=regStudents;
            localStorage.setItem("selectedCourse",JSON.stringify(course));
            start();
        },
        error: function(err){
          console.log(err);
        }
    
      });
}

//comments:
var ratingValueToBeSend=0;
function myCommentStyle(){
const allStar = document.querySelectorAll('.rating .star')
const ratingValue = document.querySelector('.rating input')

allStar.forEach((item, idx)=> {
	item.addEventListener('click', function () {
		let click = 0
		ratingValue.value = idx + 1
		allStar.forEach(i=> {
			i.classList.replace('bxs-star', 'bx-star')
			i.classList.remove('active')
		})
		for(let i=0; i<allStar.length; i++) {
			if(i <= idx) {
				allStar[i].classList.replace('bx-star', 'bxs-star')
				allStar[i].classList.add('active')
			} else {
				allStar[i].style.setProperty('--i', click)
				click++
			}
		}
        console.log(idx+1); //rating
        ratingValueToBeSend=idx+1;
	})
})
}
/*
var feedback=[
    {
        "userID": 1,
        "name": "klei",
        "surname": "null",
        "email": "benjamin.smith11@fti.edu.al",
        "rating": 4,
        "comment": "Fantastik",
        "date": "2023-12-16"
    },
    {
        "userID": 2,
        "name": "john",
        "surname": "xt",
        "email": "asd.smith11@fti.edu.al",
        "rating": 3,
        "comment": "Interesant",
        "date": "2023-12-11"
    }
];
*/
function getComments(courseId){
    $.ajax({
      type:"GET",
      url:"http://localhost:8080/feedbacks/"+courseId, 
      success: function(data){
          console.log(data);
          showCmments(data);
      },
      error: function(err){
        console.log(err);
        showCmments(false);
      }
  
    });
}

function showCmments(feedback){
    if(feedback===false){
        showMyComment();
        return;
    }
    var commentsRef=document.getElementById('others-comments');
    commentsRef.innerHTML=``;
    //console.log(typeof feedback);
    if (typeof feedback === 'object' && feedback.length==0){
        commentsRef.innerHTML="...No comment yet!";
        showMyComment(); 
        return;
    }

    for(var f of feedback){
        var stars=`<div class="rating2">`;
        var i;
        for(i=0;i<f.rating;i++){
            stars+=`<i class='bx bxs-star star active' style="--i: `+i+`;"></i>`
        }
        for(;i<5;i++)
            stars+=`<i class='bx bx-star star' style="--i: `+i+`;"></i>`;
        stars+=`</div>`
        commentsRef.innerHTML+=`
            <hr style="margin-top: 10px;">
            <p>`+f.name+` `+ f.surname+`<b style="margin-left:40px">Date:</b> `+f.date+`</p>
            `+stars+`
        <textarea name="opinion" cols="30" rows="3" readonly>`+f.comment+`</textarea>`;
    }
    showMyComment();
}
function showMyComment(){
    
    var course=JSON.parse(localStorage.getItem("selectedCourse"));
    var user=JSON.parse(localStorage.getItem("logedInUser"));
    var myCommentRef=document.getElementById("my-comment");
    myCommentRef.innerHTML=``;
    if(course.userRegistered===true){
       
        myCommentRef.innerHTML=`
        <hr style="margin-top: 10px;">
        <p>Dear `+user.name+` we'd love to hear your thoughts</p>
        <div class="rating" id="rating">
            <input type="number" name="rating" hidden>
            <i class='bx bx-star star' style="--i: 0;"></i>
            <i class='bx bx-star star' style="--i: 1;"></i>
            <i class='bx bx-star star' style="--i: 2;"></i>
            <i class='bx bx-star star' style="--i: 3;"></i>
            <i class='bx bx-star star' style="--i: 4;"></i>
        </div>
        <textarea name="opinion" cols="30" rows="3" placeholder="Your opinion..." id="comment"></textarea>
        <div class="btn-group">
            <button onClick="sendData()" class="green">Submit</button>
            <button onClick="clearComment()" class="btn cancel">Cancel</button>
        </div>
        `;
        myCommentStyle();
        
    }
    else  myCommentRef.innerHTML=``;
}

function sendData(){
    var course=JSON.parse(localStorage.getItem("selectedCourse"));
    var user=JSON.parse(localStorage.getItem("logedInUser"));
    var commentRef=document.getElementById("comment");
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = padNumber(currentDate.getMonth() + 1); 
    var day = padNumber(currentDate.getDate());
    var formattedDate = year + '-' + month + '-' + day;

    var feedback={
        userID: user.id,
        name: user.name,
        surname: user.surname,
        email: user.email,
        rating: ratingValueToBeSend,
        comment: commentRef.value,
        date: formattedDate
    }
    $.ajax({
        type:"POST",
        url:"http://localhost:8080/feedback/"+course.id,
        data:JSON.stringify(feedback), 
        contentType: "application/json",
        success: function(data){
            getComments(course.id);
        },
        error: function(err){
            console.log(err);
          Swal.fire({
              icon: "error",
              title: "Oops...",
              text: err.responseText
            });
        }
    
      });
}
function padNumber(number) {
    return (number < 10 ? '0' : '') + number;
}

function clearComment(){
    document.getElementById("comment").value="";
}