import { initializeApp } from "https://www.gstatic.com/firebasejs/9.23.0/firebase-app.js";
import {
  getAuth,
  signInWithPopup,
  GoogleAuthProvider,
  onAuthStateChanged,
  signOut,
} from "https://www.gstatic.com/firebasejs/9.23.0/firebase-auth.js";

const firebaseConfig = {
  apiKey: "AIzaSyBfKica-6MAXJXJDNY4XMxsktLAIB6u6i4",
  authDomain: "may2023deleteme.firebaseapp.com",
  projectId: "may2023deleteme",
  appId: "1:460858244543:web:e57a049a6211ccb6f0f50a",
};
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const btnLogin = document.querySelector("#btnLogin");
const btnLogout = document.querySelector("#btnLogout");
const notLoggedIn = document.querySelector("#not-logged-in");
const loggedIn = document.querySelector("#logged-in");
const whoami = document.querySelector("#whoami");
const myId = document.querySelector("#my-id");
const newId = document.querySelector("#new-id");

var currentUser = null;
const monitorAuthState = async () => {
  onAuthStateChanged(auth, (user) => {
    console.log(auth, user);
    if (user) {
      currentUser = user;
      console.log(user.displayName);
      identifyMe();
      notLoggedIn.style.display = "none"; // Hide login button
      loggedIn.style.display = "block"; // Show logout button
      // THE REAL WORK WOULD HAPPEN HERE
      // showApp()
      // showLoginState(user)
      // hideLoginError()
      // hideLinkError()
    } else {
      const nobody = "Nobody Logged In";
      currentUser = { displayName: nobody };
      notLoggedIn.style.display = "block"; // Show login button
      loggedIn.style.display = "none"; // Hide logout button
      // showLoginForm()
      console.log("Nobody Logged In");
    }
  });
};
const signMeIn = async () => {
  console.log("signMeIn");
  const userCred = await signInWithPopup(auth, new GoogleAuthProvider());
  currentUser = userCred.user;
};

const signMeOut = async () => {
  signOut(auth)
    .then(() => {
      currentUser = { displayName: "Nobody Logged In" };
      identifyMe();
      notLoggedIn.style.display = "block"; // Show login button
      loggedIn.style.display = "none"; // Hide logout button
    })
    .catch((error) => {
      alert(error);
    });
};

const identifyMe = () => {
  if (currentUser) {
    whoami.innerHTML = currentUser.displayName;
    myId.innerHTML = currentUser.uid;
    newId.value = currentUser.uid;
  } else {
    whoami.innerHTML = "Nobody Logged In";
  }
};

btnLogin.addEventListener("click", signMeIn);
btnLogout.addEventListener("click", signMeOut);
monitorAuthState();
