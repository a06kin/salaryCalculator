function netCalculation() {
    var overall = document.getElementById("overall").value;
    document.getElementById("overallValue").innerHTML = "Overall value = " + overall;

    var social = overall * 0.1005;
    var IIN = (overall - social - 75) * 0.23;
    var net = overall - social - IIN;

    document.getElementById("netValue").innerHTML = "Net value = " + net;
}
