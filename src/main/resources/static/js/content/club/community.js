


function searchForm(){
   
   
   const searchText2 = document.querySelector('#searchText2');
   searchText2.value = searchText2.value.trim();
   
   if (searchText2.value == '') {
      alert(111);
      return;
   }

   const selectOption2 = document.querySelector('#searchOption2');
   const searchOption = document.querySelector('#searchOption');
   const searchText = document.querySelector('#searchText');
   
   searchOption.value = selectOption2.value;
   searchText.value = searchText2.value;
   
  document.querySelector('#searchForm').submit();
   
}

function page(nowPage){
   
   document.querySelector('#nowPageNum').value=nowPage;
   document.getElementById("searchForm").submit();
   
   
}