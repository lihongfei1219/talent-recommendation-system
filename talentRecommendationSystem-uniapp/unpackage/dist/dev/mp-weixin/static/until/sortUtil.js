//根据不同需求进行排序

/**数组根据数组对象中的某个属性值进行排序的方法 
     * 使用例子：newArray.sort(sortBy('number',false)) //表示根据number属性降序排列;若第二个参数不传递，默认表示升序排序
     * @param attr 排序的属性 如number属性
     * @param rev true表示升序排列，false降序排序
     * */
/**
age: (...)
id: (...)
name: (...)
phone: (...)
photo: (...)
rewards: (...)
skills: (...)
successNum: (...)
talent: (...)
workTime: (...)

*/
function compare(property){
    return function(a,b){
        var value1 = a[property];
        var value2 = b[property];
        return value2 - value1;
    }
}
// console.log(arr.sort(compare('age')))
export default{
	compare
}
