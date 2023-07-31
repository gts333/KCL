function toReadable(original, isEndTime){
    var weekDayNum = original.substring(0, 2);
    var weekDay;
    switch (weekDayNum) {
        case "01":
            weekDay = "MON";
            break;
        case "02":
            weekDay = "TUE";
            break;
        case "03":
            weekDay = "WED";
            break;
        case "04":
            weekDay = "THU";
            break;
        case "05":
            weekDay = "FRI";
            break;
        case "06":
            weekDay = "SAT";
            break;
        case "07":
            weekDay = "SUN";
            break;

    }
    var hour = original.substring(3, 5);
    var tenMinuteNum = original.substring(6, 8);
    var tenMinute;
    switch (tenMinuteNum) {
        case "01":
            tenMinute = isEndTime ? "10" : "00";
            break;
        case "02":
            tenMinute = isEndTime ? "20" : "10";
            break;
        case "03":
            tenMinute = isEndTime ? "30" : "20";
            break;
        case "04":
            tenMinute = isEndTime ? "40" : "30";
            break;
        case "05":
            tenMinute = isEndTime ? "50" : "40";
            break;
        case "06":
            tenMinute = isEndTime ? "60" : "50";
            break;
    }
    return weekDay + ":" + hour + ":" + tenMinute;
}

function toOriginal(readable, isEndTime){
    var weekDayRaw = readable.substring(0, 3);
    var weekDay;
    switch (weekDayRaw) {
        case "MON":
            weekDay = "01";
            break;
        case "TUE":
            weekDay = "02";
            break;
        case "WED":
            weekDay = "03";
            break;
        case "THU":
            weekDay = "04";
            break;
        case "FRI":
            weekDay = "05";
            break;
        case "SAT":
            weekDay = "06";
            break;
        case "SUN":
            weekDay = "07";
            break;
    }
    var hour = readable.substring(4, 6);
    var tenMinuteNum = readable.substring(7, 9);
    var tenMinute;
    switch (tenMinuteNum) {
        case "00":
            tenMinute = "01";
            break;
        case "10":
            tenMinute = isEndTime ? "01" : "02";
            break;
        case "20":
            tenMinute = isEndTime ? "02" : "03";
            break;
        case "30":
            tenMinute = isEndTime ? "03" : "04";
            break;
        case "40":
            tenMinute = isEndTime ? "04" : "05";
            break;
        case "50":
            tenMinute = isEndTime ? "05" : "06";
            break;
        case "60":
            tenMinute = "06";
            break;
    }
    return weekDay + "_" + hour + "_" + tenMinute;
}