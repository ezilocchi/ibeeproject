/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
function Validar(){
     if ( $("#j_id298:consultarCajones:id_do_campo").val() == ""){
          alert("O campo tal está vazio");
          // se vc quiser mudar a cor da borda do campo, vc pode fazer isso aqui ainda
          $("#j_id298:consultarCajones:id_do_campo").css("border", "1px solid #F60");
     }
     //.... adicione outros ifs se vc quiser
}

