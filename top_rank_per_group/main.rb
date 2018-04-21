# Top rank per group: http://rosettacode.org/wiki/Top_rank_per_group
# 各部署ごとに給料ランキングベストN位を出力する。

require 'csv'

n = 3

table = CSV.new(DATA, headers: true, header_converters: :symbol).group_by{|row| row[:department]}
table.each do |department, rows|
  puts "Department: #{department}"
  rows.sort_by{|row| -1 * row[:salary].to_i}.take(n).each do |row|
    puts "#{row[:employee_name]} (#{row[:employee_id]}) #{row[:salary]}"
  end
  puts
end

# Department: D101
# George Woltman (E00127) 53500
# David McClellan (E04242) 41500
# Tyler Bennett (E10297) 32000
# 
# Department: D050
# John Rappl (E21437) 47000
# Nathan Adams (E41298) 21900
# 
# Department: D202
# Rich Holcomb (E01234) 49500
# Claire Buckman (E39876) 27800
# David Motsinger (E27002) 19250
# 
# Department: D190
# Kim Arlich (E10001) 57000
# Timothy Grove (E16398) 29900


__END__
Employee Name,Employee ID,Salary,Department
Tyler Bennett,E10297,32000,D101
John Rappl,E21437,47000,D050
George Woltman,E00127,53500,D101
Adam Smith,E63535,18000,D202
Claire Buckman,E39876,27800,D202
David McClellan,E04242,41500,D101
Rich Holcomb,E01234,49500,D202
Nathan Adams,E41298,21900,D050
Richard Potter,E43128,15900,D101
David Motsinger,E27002,19250,D202
Tim Sampair,E03033,27000,D101
Kim Arlich,E10001,57000,D190
Timothy Grove,E16398,29900,D190